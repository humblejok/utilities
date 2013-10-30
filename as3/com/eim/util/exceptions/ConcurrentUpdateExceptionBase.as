/**
 * Generated by Gas3 v2.2.1 (Granite Data Services).
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERWRITTEN EACH TIME YOU USE
 * THE GENERATOR. INSTEAD, EDIT THE INHERITED CLASS (ConcurrentUpdateException.as).
 */

package com.eim.util.exceptions {

    import com.eim.util.model.Stamp;
    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;

    [Bindable]
    public class ConcurrentUpdateExceptionBase extends ConcurrentException {

        protected var _stamp:Stamp;

        [Bindable(event="unused")]
        public function get stamp():Stamp {
            return _stamp;
        }

        public override function readExternal(input:IDataInput):void {
            super.readExternal(input);
            _stamp = input.readObject() as Stamp;
        }

        public override function writeExternal(output:IDataOutput):void {
            super.writeExternal(output);
            output.writeObject(_stamp);
        }
    }
}