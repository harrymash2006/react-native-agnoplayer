import {NativeModules, NativeEventEmitter, Platform, DeviceEventEmitter} from 'react-native';

const {AgnoPlay} = NativeModules;

const AgnoPlayEmitter = new NativeEventEmitter(AgnoPlay);

export default {

    onStartEvent(error, videoQuality){
        //return AgnoPlay.onStart(error, videoQuality);
    },

    emitter: Platform.OS === "ios" ? AgnoPlayEmitter : DeviceEventEmitter,
};