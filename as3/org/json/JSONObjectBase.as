/**
 * Generated by Gas3 v2.2.1 (Granite Data Services).
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERWRITTEN EACH TIME YOU USE
 * THE GENERATOR. INSTEAD, EDIT THE INHERITED CLASS (JSONObject.as).
 */

package org.json {

    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    import flash.utils.IExternalizable;
    import org.granite.collections.IMap;

    [Bindable]
    public class JSONObjectBase implements IExternalizable {

        private var _map:IMap;

        public function readExternal(input:IDataInput):void {
            _map = input.readObject() as IMap;
        }

        public function writeExternal(output:IDataOutput):void {
            output.writeObject(_map);
        }
    }
}