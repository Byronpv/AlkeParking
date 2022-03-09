const val MAXIMUM_QUOTS = 20

data class Parking(val vehicles: MutableSet<Vehicle>) {

    fun addVehicle(vehicle: Vehicle): Boolean {
        val availableQuotas = when (vehicles.size < MAXIMUM_QUOTS) {
            true -> vehicles.add(vehicle)
            else -> false
        }
        return availableQuotas
    }


}

