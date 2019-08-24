import React from 'react'
import { View, requireNativeComponent, StyleSheet, Platform } from 'react-native'

const moduleName = Platform.OS === 'ios' ? 'MapView' : 'MapViewManager'

const TomTomMap = requireNativeComponent(moduleName)

class App extends React.Component {
  constructor(props){
    super(props)
  }
  onMapReady = () => {
    alert('MAP READY')
  }

  render () {
    return (
      <>
        <TomTomMap
          onMapReady={this.onMapReady}
          markers={[{ lat: 40.9175, lng: 38.3927, label: 'Giresun' }, { lat: 40.9862, lng: 37.8797, label: 'Ordu' }]}
          mapZoom={7}
          mapCenter={{ lat: 40.9175, lng: 38.3927 }}
          style={StyleSheet.absoluteFillObject}
        />
      </>
    )
  }
}

export default App
