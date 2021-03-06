/*
 * Copyright 2017 The Mifos Initiative.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.mifos.rhythm.api.v1.events;

/**
 * @author Myrle Krantz
 */
@SuppressWarnings("unused")
public interface EventConstants {

  String DESTINATION = "rhythm-v1";
  String SELECTOR_NAME = "action";
  String INITIALIZE = "initialize";
  String POST_APPLICATION = "post-application";
  String POST_BEAT = "post-beat";
  String DELETE_APPLICATION = "delete-application";
  String DELETE_BEAT = "delete-beat";
  String SELECTOR_INITIALIZE = SELECTOR_NAME + " = '" + INITIALIZE + "'";
  String SELECTOR_POST_APPLICATION = SELECTOR_NAME + " = '" + POST_APPLICATION + "'";
  String SELECTOR_POST_BEAT = SELECTOR_NAME + " = '" + POST_BEAT + "'";
  String SELECTOR_DELETE_APPLICATION = SELECTOR_NAME + " = '" + DELETE_APPLICATION + "'";
  String SELECTOR_DELETE_BEAT = SELECTOR_NAME + " = '" + DELETE_BEAT + "'";
}
