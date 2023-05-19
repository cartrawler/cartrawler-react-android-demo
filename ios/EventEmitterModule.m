//
//  EventEmitterModule.m
//  ReactDemo
//
//  Created by Gareth Harte on 18/05/2023.
//

#import "EventEmitterModule.h"

@implementation EventEmitterModule

- (NSArray<NSString *> *)supportedEvents {
  NSArray<NSString *> *arrayOfEvents = [[NSArray alloc]initWithObjects:@"BookingEvent", nil];
  return arrayOfEvents;
}

@end
