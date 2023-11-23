import {NativeModules, NativeEventEmitter, Platform, DeviceEventEmitter} from 'react-native';

const {AgnoPlay} = NativeModules;

const AgnoPlayEmitter = new NativeEventEmitter(AgnoPlay);

export default {

    emitter: Platform.OS === "ios" ? AgnoPlayEmitter : AgnoPlayEmitter
};