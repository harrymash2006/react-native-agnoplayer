//
//  RCTAgnoSwiftLog.h
//  RCTAgnoPlay
//
//  Created by HARDIK MASHRU on 03/12/23.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface RCTAgnoSwiftLog : NSObject
+ (void)error:(NSString * _Nonnull)message file:(NSString * _Nonnull)file line:(NSUInteger)line;
+ (void)warn:(NSString * _Nonnull)message file:(NSString * _Nonnull)file line:(NSUInteger)line;
+ (void)info:(NSString * _Nonnull)message file:(NSString * _Nonnull)file line:(NSUInteger)line;
+ (void)log:(NSString * _Nonnull)message file:(NSString * _Nonnull)file line:(NSUInteger)line;
+ (void)trace:(NSString * _Nonnull)message file:(NSString * _Nonnull)file line:(NSUInteger)line;
@end

NS_ASSUME_NONNULL_END
