//
//  AgnoPlayModule.m
//  react-native-agnoplayer
//
//  Created by HARDIK MASHRU on 04/12/23.
//

#import "AgnoPlayModule.h"

@implementation AgnoPlayModule
@synthesize bridge = _bridge;

RCT_EXPORT_MODULE();

- (NSArray<NSString *> *)supportedEvents {
    return @[@"onFullScreen", @"onLoad"];
}

- (void)startObserving{
    [[NSNotificationCenter defaultCenter] addObserver:self
                selector:@selector(receiveonFullScreenNotification:)
                name:@"onFullScreen"
                object:nil];
    [[NSNotificationCenter defaultCenter] addObserver:self
                selector:@selector(receiveonLoadNotification:)
                name:@"onLoad"
                object:nil];
}

-(void) receiveonLoadNotification:(NSNotification *) notification {
    NSDictionary *userInfo = notification.userInfo;
    if ([[notification name] isEqualToString:@"onLoad"]){
        if(userInfo!=nil){
            [self sendEventWithName:@"onLoad" body:userInfo];
        }
    }
}

-(void) receiveonFullScreenNotification:(NSNotification *) notification {
    NSDictionary *userInfo = notification.userInfo;
    if ([[notification name] isEqualToString:@"onFullScreen"]){
        if(userInfo!=nil){
            [self sendEventWithName:@"onFullScreen" body:userInfo];
        }
    }
}

@end
