/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://code.google.com/p/google-apis-client-generator/
 * (build: 2014-07-22 21:53:01 UTC)
 * on 2014-08-17 at 00:59:03 UTC 
 * Modify at your own risk.
 */

package com.example.agriexpensett.upaccendpoint.model;

/**
 * Model definition for UpAcc.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the upaccendpoint. For a detailed explanation see:
 * <a href="http://code.google.com/p/google-http-java-client/wiki/JSON">http://code.google.com/p/google-http-java-client/wiki/JSON</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class UpAcc extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String acc;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String address;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String county;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private Key key;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String keyrep;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key @com.google.api.client.json.JsonString
  private java.lang.Long lastUpdated;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer signedIn;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getAcc() {
    return acc;
  }

  /**
   * @param acc acc or {@code null} for none
   */
  public UpAcc setAcc(java.lang.String acc) {
    this.acc = acc;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getAddress() {
    return address;
  }

  /**
   * @param address address or {@code null} for none
   */
  public UpAcc setAddress(java.lang.String address) {
    this.address = address;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getCounty() {
    return county;
  }

  /**
   * @param county county or {@code null} for none
   */
  public UpAcc setCounty(java.lang.String county) {
    this.county = county;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public Key getKey() {
    return key;
  }

  /**
   * @param key key or {@code null} for none
   */
  public UpAcc setKey(Key key) {
    this.key = key;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getKeyrep() {
    return keyrep;
  }

  /**
   * @param keyrep keyrep or {@code null} for none
   */
  public UpAcc setKeyrep(java.lang.String keyrep) {
    this.keyrep = keyrep;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Long getLastUpdated() {
    return lastUpdated;
  }

  /**
   * @param lastUpdated lastUpdated or {@code null} for none
   */
  public UpAcc setLastUpdated(java.lang.Long lastUpdated) {
    this.lastUpdated = lastUpdated;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getSignedIn() {
    return signedIn;
  }

  /**
   * @param signedIn signedIn or {@code null} for none
   */
  public UpAcc setSignedIn(java.lang.Integer signedIn) {
    this.signedIn = signedIn;
    return this;
  }

  @Override
  public UpAcc set(String fieldName, Object value) {
    return (UpAcc) super.set(fieldName, value);
  }

  @Override
  public UpAcc clone() {
    return (UpAcc) super.clone();
  }

}
