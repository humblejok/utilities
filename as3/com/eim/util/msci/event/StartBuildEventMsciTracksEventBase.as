/**
 * Generated by Gas3 v2.2.1 (Granite Data Services).
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERWRITTEN EACH TIME YOU USE
 * THE GENERATOR. INSTEAD, EDIT THE INHERITED CLASS (StartBuildEventMsciTracksEvent.as).
 */

package com.eim.util.msci.event {

    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;

    [Bindable]
    public class StartBuildEventMsciTracksEventBase extends MsciImporterEvent {

        protected var _eventNb:int;

        public function set eventNb(value:int):void {
            _eventNb = value;
        }
        public function get eventNb():int {
            return _eventNb;
        }

        public override function readExternal(input:IDataInput):void {
            super.readExternal(input);
            _eventNb = input.readObject() as int;
        }

        public override function writeExternal(output:IDataOutput):void {
            super.writeExternal(output);
            output.writeObject(_eventNb);
        }
    }
}