import React, { PureComponent } from 'react'
import { View, Text, Image, TouchableOpacity, StyleSheet, NativeModules } from 'react-native'
import PropTypes from 'prop-types'

const { NetInfoModule } = NativeModules

const styles = StyleSheet.create({
    container: {
        backgroundColor: '#f8f8f8',
        height: '100%',
        width: '100%',
        alignItems: 'center'
    },
    image: {
        marginTop: 126,
        height: 123,
        width: 252
    },
    noInternetText: {
        fontSize: 16,
        textAlign: 'center',
        marginTop:33
    },
    verifyText: {
        textAlign: 'center',
        fontSize: 14,
        lineHeight: 21,
        flexWrap: 'wrap-reverse',
        marginHorizontal: 19,
        marginTop: 21,
        color: '#8f8f8f',
        padding: 7
    },
    button: {
        backgroundColor: '#1dd38f',
        borderRadius: 2,
        height: 48,
        width: 194,
        alignContent: 'center',
        justifyContent: 'center',
        marginTop: 50
    },
    buttonText: {
        fontSize: 18,
        fontWeight: '500',
        color: 'white',
        textAlign: 'center'
    }
})

export default class NoInternetScreen extends PureComponent {

    onPress = () => {
        const { onTryAgain } = this.props
        NetInfoModule.isNetConnected()
        .then((status) => onTryAgain(status))
        .catch((err) => {
            console.log(err)
            onTryAgain(false)
        })
    }

    renderButton = () => (
        <TouchableOpacity style={styles.button} onPress={this.onPress}>
            <Text style={styles.buttonText}>
                Try Again
            </Text>
        </TouchableOpacity>
    )

    render() {
        const { src, noInternetText, extraText } = this.props
        return (
            <View style={styles.container}>
                <Image
                    source={src}
                    style={styles.image}
                    resizeMode={"contain"}
                />
                <Text style={styles.noInternetText}>
                    {noInternetText}
                </Text>
                <Text style={styles.verifyText} >
                    {extraText}
                </Text>
                {this.renderButton()}
            </View>
        )
    }
}

NoInternetScreen.propTypes = {
    src: PropTypes.object.isRequired,
    noInternetText: PropTypes.string,
    extraText: PropTypes.string,
    onTryAgain: PropTypes.func.isRequired
}

NoInternetScreen.defaultProps = {
    noInternetText: 'No Internet Connection',
    extraText: 'Make sure Wi-fi/Data Network is on and try again'
}
