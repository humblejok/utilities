/**
 * Generated by Gas3 v2.2.1 (Granite Data Services).
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERWRITTEN EACH TIME YOU USE
 * THE GENERATOR. INSTEAD, EDIT THE INHERITED CLASS (MSCIFillerParser.as).
 */

package com.eim.util.msci {

    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    import flash.utils.IExternalizable;
    import java.io.File;
    import org.granite.collections.IMap;

    [Bindable]
    public class MSCIFillerParserBase implements IExternalizable {

        private var _descriptors:IMap;
        private var _source:File;
        private var _values:IMap;

        public function readExternal(input:IDataInput):void {
            _descriptors = input.readObject() as IMap;
            _source = input.readObject() as File;
            _values = input.readObject() as IMap;
        }

        public function writeExternal(output:IDataOutput):void {
            output.writeObject(_descriptors);
            output.writeObject(_source);
            output.writeObject(_values);
        }
    }
}