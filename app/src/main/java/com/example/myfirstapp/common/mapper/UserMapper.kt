package com.example.myfirstapp.common.mapper

import com.example.myfirstapp.screens.main.data.local.entity.LocalAddress
import com.example.myfirstapp.screens.main.data.local.entity.LocalCompany
import com.example.myfirstapp.screens.main.data.local.entity.LocalGeo
import com.example.myfirstapp.screens.main.data.local.entity.UserEntity
import com.example.myfirstapp.screens.main.data.models.Address
import com.example.myfirstapp.screens.main.data.models.Company
import com.example.myfirstapp.screens.main.data.models.Geo
import com.example.myfirstapp.screens.main.data.models.User
import com.example.myfirstapp.screens.main.data.remote.model.RemoteAddress
import com.example.myfirstapp.screens.main.data.remote.model.RemoteCompany
import com.example.myfirstapp.screens.main.data.remote.model.RemoteGeo
import com.example.myfirstapp.screens.main.data.remote.model.RemoteUser

fun RemoteGeo.toGeo(): Geo {
    return Geo(
        lat = lat,
        lng = lng
    )
}

fun LocalGeo.toGeo(): Geo {
    return Geo(
        lat = lat,
        lng = lng
    )
}

fun Geo.toRemoteGeo(): RemoteGeo {
    return RemoteGeo(
        lat = lat,
        lng = lng
    )
}

fun Geo.toLocalGeo(): LocalGeo {
    return LocalGeo(
        lat = lat,
        lng = lng
    )
}

fun RemoteAddress.toAddress(): Address {
    return Address(
        city = city,
        geo = geo.toGeo(),
        street = street,
        zipcode = zipcode,
        suite = suite
    )
}

fun LocalAddress.toAddress(): Address {
    return Address(
        city = city,
        geo = localGeo.toGeo(),
        street = street,
        zipcode = zipcode,
        suite = suite
    )
}

fun Address.toRemoteAddress(): RemoteAddress {
    return RemoteAddress(
        city = city,
        geo = geo.toRemoteGeo(),
        street = street,
        zipcode = zipcode,
        suite = suite
    )
}

fun Address.toAddress(): LocalAddress {
    return LocalAddress(
        city = city,
        localGeo = geo.toLocalGeo(),
        street = street,
        zipcode = zipcode,
        suite = suite
    )
}

fun RemoteCompany.toCompany(): Company {
    return Company(
        bs = bs,
        catchPhrase = catchPhrase,
        name = name
    )
}

fun LocalCompany.toCompany(): Company {
    return Company(
        bs = bs,
        catchPhrase = catchPhrase,
        name = companyName
    )
}

fun Company.toRemoteCompany(): RemoteCompany {
    return RemoteCompany(
        bs = bs,
        catchPhrase = catchPhrase,
        name = name
    )
}

fun Company.toCompany(): LocalCompany {
    return LocalCompany(
        bs = bs,
        catchPhrase = catchPhrase,
        companyName = name
    )
}

fun RemoteUser.toUser(): User {
    return User(
        id = id,
        address = address.toAddress(),
        company = company.toCompany(),
        email = email,
        name = name,
        phone = phone,
        username = username,
        website = website,
    )
}

fun UserEntity.toUser(): User {
    return User(
        id = id,
        address = localAddress.toAddress(),
        company = localCompany.toCompany(),
        email = email,
        name = name,
        phone = phone,
        username = username,
        website = website,
    )
}

fun User.toUserEntity(): UserEntity {
    return UserEntity(
        id = id,
        localAddress = address.toAddress(),
        localCompany = company.toCompany(),
        email = email,
        name = name,
        phone = phone,
        username = username,
        website = website,
    )
}

fun User.toRemoteUser(): RemoteUser {
    return RemoteUser(
        id = id,
        address = address.toRemoteAddress(),
        company = company.toRemoteCompany(),
        email = email,
        name = name,
        phone = phone,
        username = username,
        website = website,
    )
}

