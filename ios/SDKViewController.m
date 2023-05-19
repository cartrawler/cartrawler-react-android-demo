//
//  SDKViewController.m
//  ReactDemo
//
//  Created by Gareth Harte on 09/05/2023.
//

#import "SDKViewController.h"
#import <CarTrawlerSDK/CarTrawlerSDK.h>
#import "EventEmitterModule.h"

@interface SDKViewController ()<CarTrawlerSDKDelegate>

@end

@implementation SDKViewController

- (void)viewDidLoad {
  [super viewDidLoad];
}

- (void)launchStandalone {
  CTContext *context = [[CTContext alloc] initWithImplementationID:@"89e36078-362c-4f52-9a65-ec9cea9b8482" clientID:@"685051" flow:CTFlowTypeStandAlone];
  context.delegate = self;
  [CarTrawlerSDK.sharedInstance presentFromViewController:self context:context];
}

- (void)launchInPath {
  CTContext *context = [[CTContext alloc] initWithImplementationID:@"89e36078-362c-4f52-9a65-ec9cea9b8482" clientID:@"685051" flow:CTFlowTypeInPath];
  context.countryCode = @"IE";
  context.currencyCode = @"EUR";
  context.languageCode = @"EN";
  context.pickupLocation = @"DUB";
  context.pickupDate = [NSDate dateWithTimeIntervalSinceNow:86400];
  context.delegate = self;
  [CarTrawlerSDK.sharedInstance setContext:context];
  [CarTrawlerSDK.sharedInstance presentInPathFromViewController:self];
}

- (void)didReceiveReservationDetails:(CTReservationDetails *)reservationDetails {
  
  EventEmitterModule *eventModule = [[EventEmitterModule alloc] init];
  [eventModule sendEventWithName:@"BookingEvent" body:@"hi from iOS"];
  
}

@end
