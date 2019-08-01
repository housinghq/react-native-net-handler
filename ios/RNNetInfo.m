
#if __has_include("RCTBridgeModule.h")
#import "RCTBridgeModule.h"
#else
#import <React/RCTBridgeModule.h>
#endif
#import "RNNetInfo-Swift.h"
#import "RNNetInfo.h"

@implementation RNNetInfo

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}

- (instancetype)init
{
    self = [super init];
    if (self) {
        _handler = [[ReachabilityHandler alloc] init];
    }
    return self;
}

RCT_EXPORT_MODULE(NetInfoModule)

RCT_REMAP_METHOD(isNetConnected,
                  resolver:(RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject)
{
    @try {
        bool connStatus = _handler.getConnectionStatus;
        resolve([NSNumber numberWithBool:(connStatus)]);
    } @catch(NSError * e) {
        reject(@"Null_Object", @"Handler is not initialized or null", e);
    }
}


@end
