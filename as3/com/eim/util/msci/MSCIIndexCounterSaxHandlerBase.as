/**
 * Generated by Gas3 v2.2.1 (Granite Data Services).
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERWRITTEN EACH TIME YOU USE
 * THE GENERATOR. INSTEAD, EDIT THE INHERITED CLASS (MSCIIndexCounterSaxHandler.as).
 */

package com.eim.util.msci {

    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    import flash.utils.IExternalizable;
    import mx.collections.ListCollectionView;

    [Bindable]
    public class MSCIIndexCounterSaxHandlerBase implements IExternalizable {

        protected var _indexNb:int;
        protected var _nodePathStack:ListCollectionView;

        [Bindable(event="unused")]
        public function get indexNb():int {
            return _indexNb;
        }

        public function readExternal(input:IDataInput):void {
            _indexNb = input.readObject() as int;
            _nodePathStack = input.readObject() as ListCollectionView;
        }

        public function writeExternal(output:IDataOutput):void {
            output.writeObject(_indexNb);
            output.writeObject(_nodePathStack);
        }
    }
}