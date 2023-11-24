import {NativeModules, NativeEventEmitter} from 'react-native';

const {AgnoPlay} = NativeModules;

const AgnoPlayEmitter = new NativeEventEmitter(AgnoPlay);

export default {

    emitter: AgnoPlayEmitter
};