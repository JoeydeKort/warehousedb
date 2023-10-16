package be.intecbrussel.service;

import be.intecbrussel.model.Key;
import be.intecbrussel.model.Person;
import be.intecbrussel.repository.KeyRepository;

public class KeyService {

    private final KeyRepository keyRepository = new KeyRepository();
    private final StorageService storageService = Service.getStorageService();

    public void addKey(Key key) {
        if (key.getStorage() != null) {
            storageService.addStorage(key.getStorage());
        }

        keyRepository.createKey(key);
    }

    public Key getKey(long id) {
        return keyRepository.readKey(id);
    }

    public void updateKey(Key key) {
        keyRepository.updateKey(key);
    }


    public void deleteKey(long id) {
        Key key = getKey(id);

        keyRepository.deletePersonKeyOwnershipRecords(id);

        if (key.getStorage() != null) {
            key.getStorage().setKey(null);
            storageService.updateStorage(key.getStorage());
        }
        keyRepository.deleteKey(id);
    }


}

