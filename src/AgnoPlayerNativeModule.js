import {NativeModules, NativeEventEmitter, Platform} from 'react-native';


const AgnoPlayEmitter = new NativeEventEmitter(Platform.OS === 'android' ? NativeModules.AgnoPlay : NativeModules.AgnoPlayManager);

export default {
    nativeModule: Platform.OS === 'android' ? NativeModules.AgnoPlay: NativeModules.AgnoPlayManager,
    emitter: AgnoPlayEmitter
};