//
//  ReachabilityObserverDelegate.swift
//  RNNetInfo
//
//  Created by Manthan Patel on 7/19/19.
//  Copyright © 2019 Facebook. All rights reserved.
//

import Foundation

//Reachability
//declare this property where it won't go out of scope relative to your listener
fileprivate var reachability: Reachability!

protocol ReachabilityActionDelegate {
    func reachabilityChanged(_ isReachable: Bool)
}

protocol ReachabilityObserverDelegate: class, ReachabilityActionDelegate {
    func addReachabilityObserver()
    func removeReachabilityObserver()
}

// Declaring default implementation of adding/removing observer
extension ReachabilityObserverDelegate {
    
    /** Subscribe on reachability changing */
    func addReachabilityObserver() {
        reachability = Reachability()
        
        reachability.whenReachable = { [weak self] reachability in
            self?.reachabilityChanged(true)
        }
        
        reachability.whenUnreachable = { [weak self] reachability in
            self?.reachabilityChanged(false)
        }
        
        do {
            try reachability.startNotifier()
        } catch {
            print("Unable to start notifier")
        }
    }
    
    func getCurrentConnectionStatus() -> Bool {
        
        if (reachability != nil) {
            let conn = reachability.connection;
            if (conn == .none) {
                return false
            }
            return true
        }
        return false
    }
    
    /** Unsubscribe */
    func removeReachabilityObserver() {
        if (reachability != nil) {
            reachability.stopNotifier()
            NotificationCenter.default.removeObserver(self, name: Notification.Name.reachabilityChanged, object: reachability)
            reachability = nil
        }
    }
}
