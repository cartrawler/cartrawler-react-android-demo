//
//  CTSDKStarterModule.m
//  ReactDemo
//
//  Created by Gareth Harte on 09/05/2023.
//

#import "CTSDKStarterModule.h"
#import "AppDelegate.h"
#import "EventEmitterModule.h"

@implementation CTSDKStarterModule

RCT_EXPORT_METHOD(startBookingFlow:(BOOL)isInPath) {
  [CTSDKStarterModule startBookingFlow:isInPath];
}

+ (void)startBookingFlow:(BOOL)isInPath {
  
  dispatch_async(dispatch_get_main_queue(), ^{
    SDKViewController *rootController = (SDKViewController *)[[(AppDelegate *)
                                                               [[UIApplication sharedApplication]delegate] window] rootViewController];    
    if(isInPath) {
      [rootController launchInPath];
    } else {
      [rootController launchStandalone];
    }
    
  });
  
}

RCT_EXPORT_MODULE();

@end
