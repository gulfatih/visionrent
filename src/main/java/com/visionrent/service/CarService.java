package com.visionrent.service;

import com.visionrent.domain.Car;
import com.visionrent.domain.ImageFile;
import com.visionrent.dto.CarDTO;
import com.visionrent.exception.ConflictException;
import com.visionrent.exception.ResourceNotFoundException;
import com.visionrent.exception.message.ErrorMessage;
import com.visionrent.mapper.CarMapper;
import com.visionrent.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private ImageFileService imageFileService;

    @Autowired
    private CarMapper carMapper;

    public void saveCar(UUID imageId, CarDTO carDTO) {

        // image Id image repo da var mı?
        ImageFile imageFile = imageFileService.findImageById(imageId);

        // image Id daha önce başka bir araç ile eşleşmiş mi ?
        Integer usedCarCount = carRepository.findCarCountByImageId(imageFile.getId());

        if(usedCarCount > 0){
            throw new ConflictException(ErrorMessage.IMAGE_USED_MESSAGE);
        }

        Car car = carMapper.carDTOToCar(carDTO);

        Set<ImageFile> imFiles = new HashSet<>();
        imFiles.add(imageFile);
        car.setImage(imFiles);
        carRepository.save(car);

    }

    public List<CarDTO> getAllCars() {

       List<Car> carList = carRepository.findAll();
       return carMapper.map(carList);


    }

    public Page<CarDTO> findAllWithPage(Pageable pageable) {

       Page<Car> carPage = carRepository.findAll(pageable);
       return carPage.map(new  Function<Car, CarDTO>() {
           @Override
           public CarDTO apply(Car car) {
               return carMapper.carToCarDTO(car);
           }
       });
    }

    public CarDTO findById(Long id) {
        Car car = getCar(id);
        return carMapper.carToCarDTO(car);
    }

    public Car getCar(Long id){
      return carRepository.findCarById(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessage.CAR_NOT_FOUND_MESSAGE, id)));
    }


}
