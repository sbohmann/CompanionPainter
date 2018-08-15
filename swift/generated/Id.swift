
import Foundation

import Mutabor

public struct Id : Struct
{
    public var gid: Int64
    public var eid: Int64
    public var version: Int64
    
    public init(gid: Int64, eid: Int64, version: Int64)
    {
        self.gid = gid
        self.eid = eid
        self.version = version
    }
    
    public func withGid(_ value: Int64) -> Id
    {
        return Id(gid: value, eid: eid, version: version)
    }
    
    public func withEid(_ value: Int64) -> Id
    {
        return Id(gid: gid, eid: value, version: version)
    }
    
    public func withVersion(_ value: Int64) -> Id
    {
        return Id(gid: gid, eid: eid, version: value)
    }
    
    public var hashValue: Int
    {
        get
        {
            var result: Int = 1
            result = result &* 37
            result = result &+ gid.hashValue
            result = result &* 37
            result = result &+ eid.hashValue
            result = result &* 37
            result = result &+ version.hashValue
            return result
        }
    }
    
    public static func == (lhs: Id, rhs: Id) -> Bool
    {
        if lhs.gid != rhs.gid { return false }
        if lhs.eid != rhs.eid { return false }
        if lhs.version != rhs.version { return false }
        return true
    }
    
    public var description: String
    {
        get
        {
            var result = "Id {"
            result.append(" gid: ")
            result.append(gid.description)
            result.append(", eid: ")
            result.append(eid.description)
            result.append(", version: ")
            result.append(version.description)
            result.append(" }")
            return result
        }
    }
    
    public func writeToStream(_ outputStream: OutputStream) throws
    {
        try Serializer.writeTypeId(value: TypeId.Struct, outputStream: outputStream)
        try Serializer.writeCompressedInt64(value: gid, outputStream: outputStream)
        try Serializer.writeCompressedInt64(value: eid, outputStream: outputStream)
        try Serializer.writeCompressedInt64(value: version, outputStream: outputStream)
    }
    
    public static func createFromStream(_ inputStream: InputStream) throws -> Id
    {
        try Serializer.checkTypeId(expectedTypeId: TypeId.Struct, inputStream: inputStream)
        return try createFromStreamAfterIdentification(inputStream)
    }
    
    public static func createFromStreamAfterIdentification(_ inputStream: InputStream) throws -> Id
    {
        let a_gid = try Serializer.readCompressedInt64(inputStream: inputStream)
        let a_eid = try Serializer.readCompressedInt64(inputStream: inputStream)
        let a_version = try Serializer.readCompressedInt64(inputStream: inputStream)
        return Id(gid: a_gid, eid: a_eid, version: a_version)
    }
    
    public static let TypeManager = StructTypeManager<Id>({ inputStream in try Id.createFromStream(inputStream) })
}
