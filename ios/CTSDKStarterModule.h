//
//  CTSDKStarterModule.h
//  ReactDemo
//
//  Created by Gareth Harte on 09/05/2023.
//

#import <Foundation/Foundation.h>
#import <React/RCTBridgeModule.h>
#import <React/RCTEventEmitter.h>
#import "SDKViewController.h"

NS_ASSUME_NONNULL_BEGIN

@interface CTSDKStarterModule : NSObject <RCTBridgeModule>

+ (void)startBookingFlow:(BOOL)isInPath;

@end

NS_ASSUME_NONNULL_END
