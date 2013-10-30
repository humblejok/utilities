/**
 * Generated by Gas3 v2.2.1 (Granite Data Services).
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERWRITTEN EACH TIME YOU USE
 * THE GENERATOR. INSTEAD, EDIT THE INHERITED CLASS (JSONWriter.as).
 */

package org.json {

    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    import flash.utils.IExternalizable;
    import java.io.Writer;

    [Bindable]
    public class JSONWriterBase implements IExternalizable {

        private var _comma:Boolean;
        protected var _mode:String;
        private var _stack:Array;
        private var _top:int;
        protected var _writer:Writer;

        public function readExternal(input:IDataInput):void {
            _comma = input.readObject() as Boolean;
            _mode = input.readObject() as String;
            _stack = input.readObject() as Array;
            _top = input.readObject() as int;
            _writer = input.readObject() as Writer;
        }

        public function writeExternal(output:IDataOutput):void {
            output.writeObject(_comma);
            output.writeObject(_mode);
            output.writeObject(_stack);
            output.writeObject(_top);
            output.writeObject(_writer);
        }
    }
}