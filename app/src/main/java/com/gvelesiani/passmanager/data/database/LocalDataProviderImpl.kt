package com.gvelesiani.passmanager.data.database

import com.gvelesiani.passmanager.data.database.database.PasswordDatabase
import com.gvelesiani.passmanager.data.models.PasswordModel

class LocalDataProviderImpl constructor(
    private val database: PasswordDatabase
) : LocalDataProvider {
    override fun addNewPassword(pass: PasswordModel) {
        database.getPasswordDao.addNewPassword(pass)
    }

    override fun getPasswords(isInTrash: Boolean): List<PasswordModel> {
        return database.getPasswordDao.getPasswords(isInTrash)
    }

    override fun updateFavoriteState(isFavorite: Boolean, id: Int) {
        database.getPasswordDao.updateFavoriteState(isFavorite, id)
    }

    override fun getFavoriteItems(isFavorite: Boolean): List<PasswordModel> {
        return database.getPasswordDao.getFavorites(isFavorite)
    }

    override fun updateItemTrashState(isInTrash: Boolean, id: Int) {
        database.getPasswordDao.updateItemTrashState(isInTrash, id)
    }

    override fun deletePassword(passwordId: Int) {
        database.getPasswordDao.deletePassword(passwordId)
    }
}