/**
 * Generated by Gas3 v2.2.1 (Granite Data Services).
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERWRITTEN EACH TIME YOU USE
 * THE GENERATOR. INSTEAD, EDIT THE INHERITED CLASS (StatisticsOutOfBoundException.as).
 */

package com.eim.util.exceptions.statistic {

    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;

    [Bindable]
    public class StatisticsOutOfBoundExceptionBase extends StatisticsException {

        private var _datPiDate:Date;

        public override function readExternal(input:IDataInput):void {
            super.readExternal(input);
            _datPiDate = input.readObject() as Date;
        }

        public override function writeExternal(output:IDataOutput):void {
            super.writeExternal(output);
            output.writeObject(_datPiDate);
        }
    }
}