/**
 * Generated by Gas3 v2.2.1 (Granite Data Services).
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERWRITTEN EACH TIME YOU USE
 * THE GENERATOR. INSTEAD, EDIT THE INHERITED CLASS (XmlComparisonResult.as).
 */

package com.eim.util.xml {

    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    import flash.utils.IExternalizable;
    import org.jdom.Element;

    [Bindable]
    public class XmlComparisonResultBase implements IExternalizable {

        protected var _errors:int;
        protected var _modified:Element;
        protected var _original:Element;

        public function readExternal(input:IDataInput):void {
            _errors = input.readObject() as int;
            _modified = input.readObject() as Element;
            _original = input.readObject() as Element;
        }

        public function writeExternal(output:IDataOutput):void {
            output.writeObject(_errors);
            output.writeObject(_modified);
            output.writeObject(_original);
        }
    }
}