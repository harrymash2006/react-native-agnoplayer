//
//  Config.swift
//  AgnoplayerExampleApp
//
//  Created by Bram van Boldrik on 23/09/2021.
//  Copyright © 2021 Thijs Damen. All rights reserved.
//

import Foundation

enum Config {
    enum Environment: String {
        case production
        case acceptance
        case egeniq
        
        var baseURL: String {
            switch self {
            case .production:
                return "https://player.agnoplay.com/static/api/v1/"
            case .acceptance:
                return "https://player-acc.agnoplay.com/static/api/v1/"
            case .egeniq:
                return "https://player-egeniq.agnoplay.com/static/api/v1/"
            }
        }
        
        var licenseCheckBaseURL: String {
            switch self {
            case .production:
                return "https://api.agnoplay.com/prod/"
            case .acceptance:
                return "https://api-acc.agnoplay.com/acc/"
            case .egeniq:
                return "https://api-test.agnoplay.com/test/"
            }
        }
    }

    static var environment: Environment {
        set {
            Foundation.UserDefaults.standard.set(newValue.rawValue, forKey: Config.UserDefaultsKey.environment)
        }
        get {
            let value = Foundation.UserDefaults.standard.string(forKey: UserDefaultsKey.environment) ?? ""
            return Environment(rawValue: value) ?? .production
        }
    }
    

    enum UserDefaultsKey {
        static let environment: String = "environment"
    }
}
