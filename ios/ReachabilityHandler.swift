//
//  ReachabilityHandler.swift
//  RNNetInfo
//
//  Created by Manthan Patel on 7/22/19.
//  Copyright © 2019 Facebook. All rights reserved.
//

import Foundation

@objc(ReachabilityHandler)
class ReachabilityHandler: NSObject, ReachabilityActionDelegate {
    
    fileprivate var isConnected: Bool
    fileprivate var observer: ReachabilityObserver
    //MARK: Lifecycle
    
    override init() {
        isConnected = false
        observer = ReachabilityObserver()
        super.init()
        observer.delegate = self
        observer.addReachabilityObserver()
        isConnected = observer.getCurrentConnectionStatus()
    }
    
    deinit {
        observer.removeReachabilityObserver()
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
