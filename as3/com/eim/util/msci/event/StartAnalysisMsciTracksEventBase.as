/**
 * Generated by Gas3 v2.2.1 (Granite Data Services).
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERWRITTEN EACH TIME YOU USE
 * THE GENERATOR. INSTEAD, EDIT THE INHERITED CLASS (StartAnalysisMsciTracksEvent.as).
 */

package com.eim.util.msci.event {

    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;

    [Bindable]
    public class StartAnalysisMsciTracksEventBase extends MsciImporterEvent {

        protected var _fundNb:int;

        [Bindable(event="unused")]
        public function get fundNb():int {
            return _fundNb;
        }

        public override function readExternal(input:IDataInput):void {
            super.readExternal(input);
            _fundNb = input.readObject() as int;
        }

        public override function writeExternal(output:IDataOutput):void {
            super.writeExternal(output);
            output.writeObject(_fundNb);
        }
    }
}