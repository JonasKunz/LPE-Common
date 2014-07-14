/**
 * Copyright 2014 SAP AG
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.aim.mainagent.scope;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

import org.aim.api.instrumentation.AbstractScopeAnalyzer;
import org.aim.api.instrumentation.description.Restrictions;
import org.aim.api.instrumentation.description.internal.FlatScopeEntity;
import org.aim.mainagent.utils.Utils;
import org.lpe.common.util.LpeStringUtils;

/**
 * Analyzes classes scope.
 * 
 * @author Alexander Wert
 * 
 */
public class ClassScopeAnalyzer extends AbstractScopeAnalyzer {

	private Restrictions restrictions;
	private final Set<String> classNames;

	/**
	 * Constructor.
	 * 
	 * @param classNames
	 *            a set of classes to instrument
	 */
	public ClassScopeAnalyzer(Set<String> classNames) {
		this.classNames = classNames;
	}

	@Override
	public void visitClass(Class<?> clazz, Set<FlatScopeEntity> scopeEntities) {
		if (restrictions.isExcluded(clazz.getName())) {
			return;
		}

		boolean found = false;
		for (String cName : classNames) {
			if (LpeStringUtils.patternMatches(clazz.getName(), cName)) {
				found = true;
				break;
			}
		}

		if (!found) {
			return;
		}

		Set<Method> methods = new HashSet<>();
		if (!restrictions.hasModifierRestrictions() || Modifier.isPublic(restrictions.getModifier())) {
			for (Method m : clazz.getMethods()) {
				if (!restrictions.isExcluded(m.getDeclaringClass().getName())) {
					methods.add(m);
				}

			}
		}
		for (Method m : clazz.getDeclaredMethods()) {
			if (!restrictions.isModifiersExcluded(m.getModifiers())) {
				methods.add(m);
			}
		}

		for (Method method : methods) {
			scopeEntities.add(new FlatScopeEntity(method.getDeclaringClass(), Utils.getMethodSignature(method, true)));
		}
	}

	@Override
	public void setRestrictions(Restrictions restrictions) {
		this.restrictions = restrictions;

	}
}