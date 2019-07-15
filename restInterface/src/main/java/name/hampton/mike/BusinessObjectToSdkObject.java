package name.hampton.mike;
/*
 * Copyright (C) 2019 Clover Network, Inc.
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

import name.hampton.mike.internal.BusinessObject;
import name.hampton.mike.sdk.V1BusinessObject;

/**
 * Simple object conversion from internal classes to external and back.
 */
class BusinessObjectToSdkObject {

  static V1BusinessObject convert(BusinessObject from) {
    V1BusinessObject to = new V1BusinessObject();
    to.setNumber(from.getNumber());
    to.setText(from.getText());
    return to;
  }

  static BusinessObject convert(V1BusinessObject from) {
    BusinessObject to = new BusinessObject();
    to.setNumber(from.getNumber());
    to.setText(from.getText());
    return to;
  }
}
