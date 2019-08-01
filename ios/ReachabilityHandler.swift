//
//  ReachabilityHandler.swift
//  RNNetInfo
//
//  Created by Manthan Patel on 7/22/19.
//  Copyright © 2019 Facebook. All rights reserved.
//

import Foundation

@objc(ReachabilityHandler)
open class ReachabilityHandler: NSObject, ReachabilityObserverDelegate {
    
    fileprivate var isConnected: Bool
    //MARK: Lifecycle
    
    required override public init() {
        isConnected = false
        super.init()
        addReachabilityObserver()
        isConnected = getCurrentConnectionStatus()
    }
    
    deinit {
        removeReachabilityObserver()
    }
    
    //MARK: Reachability
    
    func reachabilityChanged(_ isReachable: Bool) {
        isConnected = isReachable
    }
    
    @objc
    public func getConnectionStatus() -> Bool {
        return isConnected
    }
    
}
