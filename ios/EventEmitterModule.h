//
//  EventEmitterModule.h
//  ReactDemo
//
//  Created by Gareth Harte on 18/05/2023.
//

#import <React/RCTEventEmitter.h>

NS_ASSUME_NONNULL_BEGIN

@interface EventEmitterModule : RCTEventEmitter <RCTBridgeModule>

- (void)sendEventName:(NSString *)eventName body:(id)body;
- (bool)hasListeners;

@end


NS_ASSUME_NONNULL_END
