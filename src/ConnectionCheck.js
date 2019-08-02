
import { NativeModules } from 'react-native'

const { NetInfoModule } = NativeModules

const FETCH_TIMEOUT = 750

async function checkConnection(checkSlow = true) {
    try {
        let didTimeOut = false;
        return new Promise(async function (resolve, reject) {
            const status = await NetInfoModule.isNetConnected()
            if (status && checkSlow) {
                const timeout = setTimeout(() => {
                    didTimeOut = true;
                    reject(new Error('Connection Timed Out'));
                }, FETCH_TIMEOUT);
                fetch('http://www.google.com')
                .then((response) => {
                    clearTimeout(timeout)
                    if (!didTimeOut) { resolve('Connected') }
                })
                .catch((error) => { reject(error) })
            } else if (status) {
                resolve('Connected')
            }
            else {
                reject(new Error('Not Connected'))
            }
        })
    } catch(err) {
        return new Promise((resolve, reject) => reject(err))
    }
}

export default checkConnection
