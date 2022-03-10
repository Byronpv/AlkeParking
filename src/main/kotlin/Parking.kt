const val MAXIMUM_QUOTAS = 20

data class Parking(val vehicles: MutableSet<Vehicle>) {

    fun addVehicle(vehicle: Vehicle): Boolean {
        val availableQuotas = when (vehicles.size < MAXIMUM_QUOTAS) {
            true -> vehicles.add(vehicle)
            else -> false

        }
        return availableQuotas
    }




}

