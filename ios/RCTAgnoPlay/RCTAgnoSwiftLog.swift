//
//  RCTLog.swift
//  RCTAgnoPlay
//
//  Created by HARDIK MASHRU on 03/12/23.
//

let logHeader: String = "RNA:"

func RCTLogError(_ message: String, _ file: String=#file, _ line: UInt=#line) {
  RCTAgnoSwiftLog.error(logHeader + message, file: file, line: line)
}

func RCTLogWarn(_ message: String, _ file: String=#file, _ line: UInt=#line) {
  RCTAgnoSwiftLog.warn(logHeader + message, file: file, line: line)
}

func RCTLogInfo(_ message: String, _ file: String=#file, _ line: UInt=#line) {
  RCTAgnoSwiftLog.info(logHeader + message, file: file, line: line)
}

func RCTLog(_ message: String, _ file: String=#file, _ line: UInt=#line) {
  RCTAgnoSwiftLog.log(logHeader + message, file: file, line: line)
}

func RCTLogTrace(_ message: String, _ file: String=#file, _ line: UInt=#line) {
  RCTAgnoSwiftLog.trace(logHeader + message, file: file, line: line)
}

func DebugLog(_ message: String) {
#if DEBUG
    print(logHeader + message)
#endif
}
