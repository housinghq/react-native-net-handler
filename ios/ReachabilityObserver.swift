//
//  ReachabilityObserverDelegate.swift
//  RNNetInfo
//
//  Created by Manthan Patel on 7/19/19.
//  Copyright © 2019 Facebook. All rights reserved.
//

import Foundation

protocol ReachabilityActionDelegate: class {
    func reachabilityChanged(_ isReachable: Bool)
}

class ReachabilityObserver {
    
    weak var delegate: ReachabilityActionDelegate?
    fileprivate var reachability: Reachability!
   
    /** Subscribe on reachability changing */
    func addReachabilityObserver() {
        reachability = Reachability()
        
        reachability.whenReachable = { [weak self] reachability in
            self?.delegate?.reachabilityChanged(true)
        }
        
        reachability.whenUnreachable = { [weak self] reachability in
            self?.delegate?.reachabilityChanged(false)
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
