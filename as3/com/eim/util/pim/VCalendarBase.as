/**
 * Generated by Gas3 v2.2.1 (Granite Data Services).
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERWRITTEN EACH TIME YOU USE
 * THE GENERATOR. INSTEAD, EDIT THE INHERITED CLASS (VCalendar.as).
 */

package com.eim.util.pim {

    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    import flash.utils.IExternalizable;
    import mx.collections.ListCollectionView;

    [Bindable]
    public class VCalendarBase implements IExternalizable {

        protected var _events:ListCollectionView;

        [Bindable(event="unused")]
        public function get events():ListCollectionView {
            return _events;
        }

        public function readExternal(input:IDataInput):void {
            _events = input.readObject() as ListCollectionView;
        }

        public function writeExternal(output:IDataOutput):void {
            output.writeObject(_events);
        }
    }
}