
Pod::Spec.new do |s|
    s.name         = "react-native-net-handler"
    s.version      = "1.0.5"
    s.summary      = "RNNetInfo"
    s.description  = <<-DESC
                    RNNetInfo
                     DESC
    s.homepage     = "https://github.com/housinghq/react-native-net-handler"
    s.license      = "MIT"
    # s.license      = { :type => "MIT", :file => "FILE_LICENSE" }
    s.author             = { "author" => "author@domain.cn" }
    s.platform     = :ios, "9.0"
    s.source       = { :git => "https://github.com/author/RNNetInfo.git", :tag => "master" }
    s.source_files  = "RNNetInfo/*.{h,m}"
    s.requires_arc = true
  
  
    s.dependency "React"
    s.dependency "ReachabilitySwift"
    #s.dependency "others"
  
  end