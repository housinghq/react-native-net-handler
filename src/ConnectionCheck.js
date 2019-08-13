
import { NativeModules } from 'react-native'

const { NetInfoModule } = NativeModules

const FETCH_TIMEOUT = 750

const getInfo = (status = false, isTimedOut = false) => {
    let info = {
        isConnected: false,
        statusCode: 401,
        message: 'Not Connected'
    }
    if (status) {
        info.isConnected = true
        info.statusCode = 200
    } else if (isTimedOut) {
        info.statusCode = 402
        info.message = 'Connection Timed Out'
    }
    return info
}

async function checkConnection(checkSlow = true) {
    try {
        let didTimeOut = false;
        return new Promise(async function (resolve, reject) {
            const status = await NetInfoModule.isNetConnected()
            if (status && checkSlow) {
                const timeout = setTimeout(() => {
                    didTimeOut = true;
                    reject(getInfo(false,true))
                }, FETCH_TIMEOUT)
                fetch('http://www.google.com')
                .then((response) => {
                    clearTimeout(timeout)
                    if (!didTimeOut) {
                        resolve(getInfo(true))
                    }
                })
                .catch((error) => { reject(error) })
            } else if (status) {
                resolve(getInfo(true))
            }
            else {
                reject(getInfo())
            }
        })
    } catch(err) {
        return new Promise((resolve, reject) => reject(err))
    }
}

export default checkConnection
