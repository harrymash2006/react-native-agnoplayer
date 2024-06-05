//
//  EventStorageProvider.swift
//  AgnoplayerExampleApp
//
//  Created by Thijs Damen on 13/05/2020.
//  Copyright © 2020 Thijs Damen. All rights reserved.
//

import Foundation

import AgnoplayerSDK

class EventStorageProvider : AnalyticsProvider {
    func report(event: AnalyticsEvent, playerItem: PlayerItem?) {
        events.append(event)
        delegate?.onEvent(event: event)
    }
    
    static let shared = EventStorageProvider(delegate: nil)

    weak var delegate: EventStorageProviderDelegate?
    
    init(delegate: EventStorageProviderDelegate?) {
        self.delegate = delegate
    }
    
    public var events = [AnalyticsEvent]()
    
    public func clear() {
        events.removeAll()
        delegate?.eventsCleared()
    }
}

public protocol EventStorageProviderDelegate: AnyObject {
    func onEvent(event: AnalyticsEvent)
    func eventsCleared()
}
