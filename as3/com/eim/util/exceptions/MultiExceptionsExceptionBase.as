/**
 * Generated by Gas3 v2.2.1 (Granite Data Services).
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERWRITTEN EACH TIME YOU USE
 * THE GENERATOR. INSTEAD, EDIT THE INHERITED CLASS (MultiExceptionsException.as).
 */

package com.eim.util.exceptions {

    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    import mx.collections.ListCollectionView;

    [Bindable]
    public class MultiExceptionsExceptionBase extends EIMException {

        private var _subExceptions:ListCollectionView;

        [Bindable(event="unused")]
        public function get subExceptions():ListCollectionView {
            return _subExceptions;
        }

        public override function readExternal(input:IDataInput):void {
            super.readExternal(input);
            _subExceptions = input.readObject() as ListCollectionView;
        }

        public override function writeExternal(output:IDataOutput):void {
            super.writeExternal(output);
            output.writeObject(_subExceptions);
        }
    }
}