require "json"

package = JSON.parse(File.read(File.join(__dir__, "package.json")))

Pod::Spec.new do |s|
  s.name           = 'react-native-agnoplayer'
  s.version        = package['version']
  s.summary        = package['description']
  s.description    = package['description']
  s.license        = package['license']
  s.author         = package['author']
  s.homepage       = 'https://github.com/harrymash2006/react-native-agnoplayer'
  s.source       = { :git => "https://github.com/harrymash2006/react-native-agnoplayer.git", :tag => "v#{s.version}" }
  s.ios.deployment_target = "13.4"
  ss.source_files = "ios/RCTAgnoPlay/**/*.{h,m,swift}"
  s.dependency "React-Core"
  s.dependency "https://Private-Token:glpat-8gzTx5j18FDHFPPHFc4S@gitlab.mediahuisgroup.com/core-services/AgnoPlay-iOS.git", "~> 3.1.0"
  s.default_subspec = "AgnoPlay"
  s.static_framework = true
  s.xcconfig = {
    'OTHER_LDFLAGS': '-ObjC',
  }
end