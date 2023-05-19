//
//  EventEmitterModule.h
//  ReactDemo
//
//  Created by Gareth Harte on 18/05/2023.
//

#import <React/RCTEventEmitter.h>

NS_ASSUME_NONNULL_BEGIN

@interface RCT_EXTERN_MODULE(EventEmitterModule, RCTEventEmitter)

RCT_EXTERN_METHOD(supportedEvents)

@end

NS_ASSUME_NONNULL_END
