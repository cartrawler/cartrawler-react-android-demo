//
//  EventEmitterModule.m
//  ReactDemo
//
//  Created by Gareth Harte on 18/05/2023.
//

#import "EventEmitterModule.h"

@implementation EventEmitterModule
{
  bool hasListeners;
}

RCT_EXPORT_MODULE(EventEmitterModule);

+ (id)allocWithZone:(NSZone *)zone {
  static EventEmitterModule *sharedInstance = nil;
  static dispatch_once_t onceToken;
  dispatch_once(&onceToken, ^{
    sharedInstance = [super allocWithZone:zone];
  });
  return sharedInstance;
}

- (NSArray<NSString *> *)supportedEvents {
    NSArray<NSString *> *arrayOfEvents = [[NSArray alloc]initWithObjects:@"BookingEvent", nil];
    return arrayOfEvents;
}

// Will be called when this module's first listener is added.
-(void)startObserving {
  hasListeners = YES;
  // Set up any upstream listeners or background tasks as necessary
}

// Will be called when this module's last listener is removed, or on dealloc.
-(void)stopObserving {
  hasListeners = NO;
  // Remove upstream listeners, stop unnecessary background tasks
}

-(bool)hasListeners {
  return hasListeners;
}

- (void)sendEventName:(NSString *)eventName body:(id)body {
  if (hasListeners) {
    [self sendEventWithName:eventName body:body];
  }
}

@end
