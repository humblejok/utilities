/**
 * Generated by Gas3 v2.2.1 (Granite Data Services).
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERWRITTEN EACH TIME YOU USE
 * THE GENERATOR. INSTEAD, EDIT THE INHERITED CLASS (ApplicationFailureException.as).
 */

package com.eim.util.exceptions {

    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;

    [Bindable]
    public class ApplicationFailureExceptionBase extends EIMRuntimeException {

        private var _location:String;

        [Bindable(event="unused")]
        public function get location():String {
            return _location;
        }

        public override function readExternal(input:IDataInput):void {
            super.readExternal(input);
            _location = input.readObject() as String;
        }

        public override function writeExternal(output:IDataOutput):void {
            super.writeExternal(output);
            output.writeObject(_location);
        }
    }
}