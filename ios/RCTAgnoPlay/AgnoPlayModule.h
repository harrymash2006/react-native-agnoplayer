//
//  AgnoPlayModule.h
//  react-native-agnoplayer
//
//  Created by HARDIK MASHRU on 04/12/23.
//

#import <React/RCTBridgeModule.h>
// Support React Native headers both in the React namespace, where they are in RN version 0.40+,
// and no namespace, for older versions of React Native
#if __has_include(<React/RCTEventEmitter.h>)
#import <React/RCTEventEmitter.h>
#else
#import "RCTEventEmitter.h"
#endif

@interface AgnoPlayModule : RCTEventEmitter <RCTBridgeModule>

@end
