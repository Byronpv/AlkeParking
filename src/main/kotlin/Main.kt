import java.util.*

class Main {
    companion object {
        private val parking = Parking(mutableSetOf())

        @JvmStatic
        fun main(args: Array<String>) {
            showMenu()
        }

        var option = 0

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

                    5 -> option = 5
                }

            } while (option != 5)


        }

        private fun addVehicle(vehiclePlate: String?, vehicleType: Int?, discountCard: String?) {
            var vehicleTypeEnum: VehicleType = VehicleType.BUS
            when (vehicleType) {
                1 -> vehicleTypeEnum = VehicleType.CAR
                2 -> vehicleTypeEnum = VehicleType.MOTORCYCLE
                3 -> vehicleTypeEnum = VehicleType.MINIBUS
                4 -> vehicleTypeEnum = VehicleType.BUS
            }
            val vehicle = vehiclePlate?.let { Vehicle(it, vehicleTypeEnum, Calendar.getInstance(), discountCard) }
            when (vehicle?.let { parking.addVehicle(it) }) {
                true -> println("Welcome to AlkeParking!")
                else -> println("Sorry, the has check-in failed")
            }
        }


        private fun removeVehicle(plate: String?) {
            val removeCar = parking.vehicles.find { it.plate == plate }
            println(removeCar)
            val vehicleType = removeCar?.type
            val indexVehicle = parking.vehicles.indexOf(removeCar)
            val parkingSpace = removeCar?.let { ParkingSpace(it) }
            parkingSpace?.getListVehicles(parking.vehicles)
            removeCar?.plate.let { parkingSpace?.checkOutVehicle(it,indexVehicle,vehicleType)}

        }


    }
}