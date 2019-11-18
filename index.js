Object.defineProperty(exports, "__esModule", {
    value: true
})

import NoInternetScreen from './src/NoInternetScreen'
import checkConnection from './src/ConnectionCheck'
import { NativeModule } from 'react-native'

module.exports = {
    NoInternetScreen,
    checkConnection,
    NetInfoModule : NativeModule.NetInfoModule
}
