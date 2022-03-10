import java.lang.reflect.Executable
import java.util.*

class Main {
    companion object {
        private val parking = Parking(mutableSetOf())
        lateinit var removeCar: Vehicle
        lateinit var parkingSpace: ParkingSpace


        @JvmStatic
        fun main(args: Array<String>) {
            showMenu()
        }

        private var option = 0

        private fun showMenu() {
            do {
                println("------------------------")
                println("Welcome to AlkeParking!! \n 1. Enter vehicle \n 2. Remove vehicle \n 3. View vehicles list \n 4. View earnings \n 5. Leave")
                option = readLine()?.toInt()!!
                println("Selected option $option")
                when (option) {
                    1 -> {
                        println("Add vechile type: \n 1.CAR \n 2.MOTORCYCLE \n 3.MINIBUS \n 4.BUS")
                        val vehicleType = readLine()?.toInt()
                        println("Input Plate: ")
                        val vehiclePlate = readLine()
                        println("Your have a discount card?")
                        val discountCard = readLine()
                        addVehicle(vehiclePlate, vehicleType, discountCard)
                    }
                    2 -> {
                        println("Input the plate to remove:")
                        val vehiclePlate = readLine()
                        removeVehicle(vehiclePlate)
                    }
                    3 -> {
                        println("---- Plates at AlkeParking ----")
                        parking.listVehicles(parking.vehicles)
                    }
                    4 -> parking.totalEarnings()
                }
            } while (option != 5)
        }


        private fun addVehicle(vehiclePlate: String?, vehicleType: Int?, discountCard: String?) {

            val vehicleTypeInput = getVehicleType(vehicleType)
            val vehicle = vehiclePlate?.let { Vehicle(it, vehicleTypeInput, Calendar.getInstance(), discountCard) }
            when (vehicle?.let { parking.addVehicle(it) }) {
                true -> println("Welcome to AlkeParking!")
                else -> println("Sorry, the has check-in failed")
            }
        }

        private fun getVehicleType(vehicleType: Int?): VehicleType {

            var vehicleTypeInput: VehicleType = VehicleType.BUS
            when (vehicleType) {
                1 -> vehicleTypeInput = VehicleType.CAR
                2 -> vehicleTypeInput = VehicleType.MOTORCYCLE
                3 -> vehicleTypeInput = VehicleType.MINIBUS
                4 -> vehicleTypeInput = VehicleType.BUS
            }
            return vehicleTypeInput
        }

        private fun removeVehicle(plate: String?) {

            try {
                removeCar = parking.vehicles.find { it.plate == plate }!!
                parkingSpace = ParkingSpace(removeCar)
                val vehicleType = removeCar.type
                val indexVehicle = parking.vehicles.indexOf(removeCar)
                parkingSpace.getListVehicles(parking.vehicles)
                removeCar.plate.let { parkingSpace.checkOutVehicle(plate, indexVehicle, vehicleType) }
            } catch (e: Exception) {
                println("Sorry, the check-out failed")
            }

        }


    }
}