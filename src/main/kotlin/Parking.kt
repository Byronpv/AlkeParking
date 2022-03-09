const val MAXIMUM_QUOTS = 20

data class Parking(val vehicles: MutableSet<Vehicle>) {

    fun addVehicle(vehicle: Vehicle):Boolean{
        return if(vehicles.size < MAXIMUM_QUOTS) {
            vehicles.add(vehicle)
            true
        }else{
            false
        }
    }
}