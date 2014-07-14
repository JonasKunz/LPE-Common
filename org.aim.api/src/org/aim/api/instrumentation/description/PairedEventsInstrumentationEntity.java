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
package org.aim.api.instrumentation.description;

/**
 * This instrumentation entity describes an instrumentation of paired events
 * like monitor enter / release.
 * 
 * @author Alexander Wert
 * 
 */
public class PairedEventsInstrumentationEntity extends AbstractInstrumentationEntity {
	private PairedEvent event;

	/**
	 * Default Constructor.
	 */
	public PairedEventsInstrumentationEntity() {
	}

	/**
	 * Constructor.
	 * 
	 * @param event
	 *            event to instrument
	 */
	public PairedEventsInstrumentationEntity(PairedEvent event) {
		super();
		this.event = event;
	}

	/**
	 * @return the event
	 */
	public PairedEvent getEvent() {
		return event;
	}

	/**
	 * @param event
	 *            the event to set
	 */
	public void setEvent(PairedEvent event) {
		this.event = event;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((event == null) ? 0 : event.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		PairedEventsInstrumentationEntity other = (PairedEventsInstrumentationEntity) obj;
		if (event != other.event) {
			return false;
		}
		return true;
	}

}